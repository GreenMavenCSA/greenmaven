'use strict';
/* global polymerLoader */
/*jshint unused:false*/
/*jshint -W079*/


// This is the normal conditional loader for the Web components Polyfill
if ('registerElement' in document && 'createShadowRoot' in HTMLElement.prototype && 'import' in document.createElement('link') && 'content' in document.createElement('template')) {
  // We're using a browser with native WC support!
} else {
  // Add web components polyfill...
  document.write('<script src="bower_components/webcomponentsjs/webcomponents.lite.js"><\/script>');
}

var polymerLoader = (function() {

  // Function for creating a link element and inserting it into the <head> of the html document
  function addLinkTag(elementType, address, shim, loadTrigger) {
    var tag = document.createElement('link');
    tag.rel = elementType;
    tag.href = address;
    if (shim) {
      // add the shim-shadowdom attribute
      tag.setAttribute('shim-shadowdom', '');
    }
    if (loadTrigger) {
      // This file needs to be loaded before inserting the Polymer Application
      // when finished loading it will call the polymerLoader.insertPolymerApplication() function
      tag.setAttribute('onload', 'polymerLoader.insertPolymerApplication()');
      expectedCalls++;
    }
    document.getElementsByTagName('head')[0].appendChild(tag);
  }

  var pgApploaded = false;

  function loadPolymerApplication() {
    // Only insert once.
    if (!pgApploaded) {
      addLinkTag('import', 'filestoload.html', false, true);
      pgApploaded = true;
    }
  }

  // Counter variable for insertPolymerApplication() calls
  var callCount = 0;
  var expectedCalls = 0;

  function insertPolymerApplication() {
    callCount++;
    // Only when callCount >= expectedCalls
    // The application is only inserted after all required files have loaded
    // for the application to work.
    if (callCount >= expectedCalls) {
      // here is the html that is inserted when everything is loaded.
      document.querySelector('body').innerHTML += '<greenmaven-app></greenmaven-app>';
      document.getElementById('loader').style.display = 'none';
    }
  }


  return {
    insertPolymerApplication: function() {
      insertPolymerApplication();
    },

    loadPolymerApplication: function() {
      loadPolymerApplication();
    }
  };
})(document);