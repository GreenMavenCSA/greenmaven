# FarmHacker

## Vision and Business Case

Farmhacker helps the local food movement scale up by connecting farmers and consumers via direct peer to peer exchange. 

A farmer is traditionally connected to a customer either directly (on-farm sales, farmers markets, CSA), or via a food hub intermediary, or by restaurants and wholesalers. This traditional model comes with a number of problems for both parties, as well as for the future scalability of the local/beyond-organic food movement.

Gaining entry into farmers markets (especially popular ones) can be extremely difficult for new farmers. Selling on-farm requires the farmer's time that could otherwise be spent in the field, and makes grocery logistics more difficult for consumers since on-farm markets have extremely limited hours. CSAs offload a great deal of risk onto the consumer (the customer pays even if there's no harvest, and often doesn't get to choose their items), and only appeal to a small number of people; the small number of these types of customers makes them very difficult/expensive for producers to acquire. Restaurants and wholesalers, while potentially providing a stable income and large orders, require significant price cuts and demand levels that can overwhelm smaller producers. 

In sum, the current suite of vehicles connecting producers and consumers limits the ability of the local food movement to scale, because it's too difficult for both producers and consumers to enter (or persist in) the market.

Farmhacker is a software tool that fills the gap left by traditional sales vehicles. The premise is simple: producers publish their inventory, consumers publish their grocery lists, and farmhacker works as a matchmaker. This can occur via direct search (e.g. a customer searching on a specific item to see what producers have what items, and when/where they can be procured), or via automated exchange (e.g. a customer enters a grocery list, allowing Farmhacker to pick producers based on pre-defined criteria and create a grocery shopping itinerary that's executed either by the customer himself or a third party who delivers.)

The producer benefits from a larger pool of retail customers and a market that's active 24/7. She is able to sell at a larger volume but for near-retail prices, without losing direct connection to the customer. She can also limit her time spent at farmers markets and otherwise trying to secure sales.

The consumer no longer has to risk sellouts, parking fights, bad weather, and inconvenient hours as farmers markets. None of the risk, up-front payments, and lack of selection in CSAs. None of the extreme markup from third-party retailers. And no more of the uncertainty that comes from wondering if they know who all the farmers are in the local foodscape. The consumer also enjoys lower prices as compared to traditional market vehicles.

**Anticipated Effects

1.) More new producers and small hyperlocal producers (i.e. home gardeners) are able to enter the market because of the low barrier to market exposure.

2.) Producers are able to lower their prices because the costs of acquiring new customers and maintaining relationships with exisitng customers is much lower, but they're able to maintain both higher toplines and margins because the hub/distributor is eliminated.

3.) On-the-fence local consumers enter the market in force, as it becomes possible to locally-source a much greater share of their grocery lists conveniently, with no risk, and at a lower cost than both retailers (e.g. Whole Foods) and farmers markets.

4.) With a mature logistics system in place, retailers and restaurants may also avail themselves of the service as it offers higher quality, flexibility, and authenticity than Sysco Local or U.S. Foods.


## Product Roadmap

### Epic 1: Vault
This first module allows farmers to track inventory, project revenue based on prices in the product catalog, and compare projected revenue against actual revenue (by recording point of sale). Vault is the central module that drives the rest of the system - in aggregrate across all the farms using it, Vault would effectively represent the entire catalog of local farm products available for sale in a particular region. Vault would integrate tightly with Quickbook's sales register and inventory in order to spur widespread adoption.

### Epic 2: Lipstick
E-Commerce website integrates with Farmhacker inventory/revenue. Items are displayed to customers based on average weight and price of what's in the inventory so they get an accurate idea of what they'll be paying. Famrers can enter fulfillment avenues (e.g. farmers markets, CSA pickup, on-farm pickup, etc.) and customers can choose how to receive their items. Each producer will have either a website or a Lipstick enpoint so that customers in CSA-X will be able to trace information about the sourcing of their products. Lipstick endpoints will be to be servable to an existing domain name, or be subdomained to CSA-X.

### Epic 3+: CSA-X
Customers publish their grocery lists, producers fulfill the items. A producer can be anyone from a home gardener to a local commercial farm; they can view a dashboard that shows all open orders that match items in their inventory, then bid on the items. Customers can then select the winning bids to fulfill their grocery lists. From here there are multiple fulfillment options: 1.) courier service collects items and delivers; 2.) user downloads a shopping itinerary and picks up items, 3.) producers drop orders to hub, where customers pick them up.

