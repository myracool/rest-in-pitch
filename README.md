# Rest In Pitch !

### API

|   Method	|   URI	|   Query parameters	|   Description	|
|:-:	|:-	|:-	|:-	|
|  GET 	|   /show/id	|   	|   Retrieve show informations by id	|
|  GET 	|   /search?	|   name={name}<br> genre={genre}  	|   Retrieve shows either by name (can be partial), genre, or both	|
|  GET 	|   /last	|   	|   Retrieve last updated shows	|
|  GET 	|   /user/{user}/watchlist/	|   	|   Retrieve given user watchlist (Secured)	|
|  GET 	|   /user/{user}/likes/	|   	|  Retrieve given user favourite shows (Secured) 	|
