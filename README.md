# GOATProject

### Limitations
I had a few limitations while doing this project. 
Unfortunately, I do not have a physical device for testing, so I was not able to retrieve `getlastknownlocation` as part of the  `LocationManager` class. I know that the specification had asked me to request user permission and then get their current location, but because I was working with an emulator, I was not able to implement this. I can explain how I would like to implement this:

I was going to use the `LocationManager` class as follows:
`LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
double longitude = location.getLongitude();
double latitude = location.getLatitude();`

Before the `getLastKnownLocation` function call, I would have checked to see if the permission was granted or not. Then, I would have handled the permisson request using a `onRequestPermissionsResult`. I had to change my whole implementation of `getWeatherData` to instead take a type location and reworked my implementation. 

Another limitation was the input validation and error handling for the `ManualEntry` fragment. When testing my application, please enter a valid zipcode to retrieve weather data. If the size of the array returned by the `geocoder` is 0, my app will crash. 

### Architecture
I followed the principles of MVC architecture. The adapters filled data in UI component; The models consisted of all the data; The fragments were the views. 

### Helper Classes
These are classes that I did not write, but were written by 3rd party developers. I also made sure to comment the sources I found these classes. The meat of my work is done in the adapters and fragments.

⋅⋅* `ItemClickSupport`.
⋅⋅* `GetWeather`.... I implemented the `getWeatherData` and `getHourlyWeatherData` functions, but not the `getClient`.
⋅⋅* All of the models I used were provided by DarkSky in their documentation. I had to split up some of their models to fit my requirement (exe. had to remove the fields from `DarkSky` that were not present for `Hourly`

### Lessons
Originally, I ran into a lot of problems because when I was trying to work on daily and hourly fragments, I tried to do everything in the same fragment, using the same adapters. I would run into recyclerView binding issues when going between fragments. The lesson I learned was to split up functionality and keep them seperate. 

I ended up creating two fragments, one for main view and one for hourly.
I created two view holders,  one for main view and one for hourly.
I created two adapters,  one for main view and one for hourly.
I created two layouts,  one for main view and one for hourly.

This ended up solving all the issues I had. Thank you for the opportunity, and I hope you are satisfied with my solution. I can elaborate on any details you may have. Looking forward to hearing back. 
