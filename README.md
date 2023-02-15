# kttApi
REST Api for a webservice with quotes, votes and accounts

You can complete CRUD actions on quotes, votes and accounts.
There are some examples of http requests for this api:
Delete a voite by ID:                   DELETE	http://localhost:8080/votes/1
Get a vote by ID:                       GET	http://localhost:8080/votes/1
Create a vote:                          POST	http://localhost:8080/votes             Example of data: {"voteValue":"true","votedQuoteID":"1","votedByAccID":"2"}
Delete a quote by ID:                   DELETE	http://localhost:8080/quotes/1
Replace a quote by ID:                  PUT	http://localhost:8080/quotes/1            Example of data:{"textQuote":"Be yourself, be a human.","createdByLogin":"brot"}
Get a quote by ID:                      GET	http://localhost:8080/quotes/1
Get a random quote:                     GET	http://localhost:8080/quotes/random
Get all quotes from exact login:        GET	http://localhost:8080/quotes/login/brot
Create a quote:                         POST	http://localhost:8080/quotes            Example of data:{"textQuote":"Be yourself, be a developer.","createdByLogin":"brot"}
Get all quotes:                         GET	http://localhost:8080/quotes
Delete an account by ID:                DELETE	http://localhost:8080/accounts/1
Replace an account by ID:               PUT	http://localhost:8080/accounts/1     		   Example of data:{"login":"glebaek","password":"ggg123"}
Create an account:                      POST	http://localhost:8080/accounts       		 Example of data:{"login":"brot","password":"brot099"}
Get an account by ID:                   GET	http://localhost:8080/accounts/1
Get all accounts:                       GET	http://localhost:8080/accounts
