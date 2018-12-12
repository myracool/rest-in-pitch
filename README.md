# Rest In Pitch !

### API

|   Method	|   URI	|   Query parameters	|   Description	|
|:-:	|:-	|:-	|:-	|
|  GET 	|   /show/{id}	|   	|   Retrieve show informations by id	|
|  GET 	|   /search?	|   name={name}<br> genre={genre} (multiple genres allowed)  	|   Retrieve shows by name (can be partial), and optionally genre	|
|  GET 	|   /rand/{n}	|   	|   Retrieve n random shows	|
|  GET 	|   /user/{user}/watchlist/	|   	|   Retrieve given user watchlist (Secured)	|
|  GET 	|   /user/{user}/likes/	|   	|  Retrieve given user favourite shows (Secured) 	|
