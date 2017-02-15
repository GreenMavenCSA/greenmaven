var inventoryServiceBaseURL = "http://localhost:8080";
var userAccountServiceBaseURL = "http://localhost:8080";

appProperties = {

	inventoryServiceGetCatalogEntriesServicePath : inventoryServiceBaseURL + "inventory/rest/catalogService/getCatalogEntries",
	inventoryServiceCreateCatalogEntryServicePath : inventoryServiceBaseURL + "inventory/rest/catalogService/create"

	userAccountServiceCreateAccountServicePath : userAccountServiceBaseURL + "landingPage/rest/accountService/create",
	userAccountServiceAuthenticationServicePath : userAccountServiceBaseURL + "landingPage/rest/accountService/authenticate",
	userAccountServiceGetGrowersEatersServicePath : userAccountServiceBaseURL + "landingPage/rest/accountService/getNumGrowersEaters"

}