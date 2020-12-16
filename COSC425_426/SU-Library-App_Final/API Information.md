# LibCal API Documentation

Documentation URL: https://salisbury.libcal.com/admin/api/1.1

Used for library hours and study room reservations

- Study Room Reservations make use of the "Spaces" API (not the "Room Bookings" API).
- Library Hours uses the "Hours" API.
- Requires a username and password to access
    - Username: sdisharoon1@gulls.salisbury.edu
    - Password: Use SU Password (click "Salisbury University" on login page)
	
- You can also go through the library's server to talk with the Spaces API:
	- List of Rooms and Room Metadata: http://libapps.salisbury.edu/room-reservations/app/rooms.json
	- Get Room Available Times: https://libapps.salisbury.edu/room-reservations/ajax/room_availability.php?rid=ROOM_ID
	- Reserve a Room: https://libapps.salisbury.edu/room-reservations/ajax/reserve_space.php?rid=ROOM_ID&fname=FIRST_NAME&lname=LAST_NAMEemail=EMAIL&nickname=GROUP_NAME&times=TIMES&app=2
		- TIMES should be formatted according to ISO 8601: 2020-03-30T11:00:00-04:00|2020-03-30T13:00:00-04:00 (with to and from dates separated by pipe character)
		- Date/time format is the same as returned from the LibCal API (including timezone value, which WILL NOT always be -04:00)
		- Always set the "app" parameter to 2
		- Response will be a "booking ID" starting with "cs_" (something like "cs_88wg8QCb") if a booking is created.
		- Only allowed bookings will be created, so you must handle errors if they happen. The request will return an error mesage that can be displayed to the user if an error occurs, although the wording isn't great for some errors.
		- Bookings might be rejected if:
			- they don't have the right email domain (only "@gulls.salisbury.edu" and "@salisbury.edu" are allowed)
			- the user has made too many bookings in a particular day or week
			- the user has been banned from making reservations
			- the dates or times are invalid (must be either X:00 or X:30, for example) or outside of library hours
			- possibly other reasons as well
		
Test Room ID: 64713

Example Availability URL: https://libapps.salisbury.edu/room-reservations/ajax/room_availability.php?rid=64713
Example Booking URL: https://libapps.salisbury.edu/room-reservations/ajax/reserve_space.php?rid=64713&fname=Chris&lname=Woodall&email=cmwoodall@salisbury.edu&nickname=Test&times=2020-04-03T15:00:00-04:00|2020-04-03T17:00:00-04:00&app=2


# LibraryH3lp (Chat)


Documentation URL: https://dev.libraryh3lp.com/
Presence API URL: https://libraryh3lp.com/presence/jid/su-allstaff/chat.libraryh3lp.com/text
Embedded Web Chat URL: https://us.libraryh3lp.com/chat/su-allstaff@chat.libraryh3lp.com?skin=22280&identity=Librarian

Used for chat boxes throughout the app

In the iOS app, for simplicity we simply embed a web view with the chat widget. The only part of the API used is the presence check: https://dev.libraryh3lp.com/presence.html


# Library News Feed

URL: https://libapps.salisbury.edu/news/json.php

- A simple JSON file with a list of items to display in the news feed. 
- Note that there is a display_in_app flag that will indicate if an item should be displayed in the app, so be sure not to display items where that is set to 0.
- There are 3 date fields:
    - post_date indicates the date that should appear on the news item
    - start_date indicates the date that the item should begin appearing in the news feed
    - end_date is the day that the item should be removed from the news feed
- Images
    - The image field will only provide the filename. The full URL will be https://libapps.salisbury.edu/news/images/<filename>
    - There will be 3 images for each news item:
        - <filename>.<ext> is a 798px x 693 px image. This is for the website and should not be used in the app.
        - <filename>@2x.<ext> is a 160px x 160px image.
        - <filename>@3x.<ext> is a 240px x 240px image.


# Device Availability

URL: https://libapps.salisbury.edu/coll-man/device-circ/api/available.php

- JSON file with the current status of the devices the library circulates
- Status values are just integers, which map to these user-facing statuses:
    - 1 = Available
    - 2 = Checked Out
    - 3, 4, 5, 10, 11 = Temporarily Unavailable
    - 6, 7, 8, 9, 12, 13 = Do not display at all
	
        
# Hosted Resource Files

The iOS app can update some of its data by changing the information in these hosted files. When changes are needed, the version number in the version.json file is incremented by 1, indicating to the app that new resource files should be downloaded. These files are iOS/macOS "Property List" files, which are really just XML files. You can read more them about here: https://en.wikipedia.org/wiki/Property_list

- https://libapps.salisbury.edu/app/resources/Staff.plist
- https://libapps.salisbury.edu/app/resources/StaffDisplayOrder.plist
- https://libapps.salisbury.edu/app/resources/Databases.plist
- https://libapps.salisbury.edu/app/resources/GuideSubjects.plist
- https://libapps.salisbury.edu/app/resources/version.json

# Emergency Notices

URL: https://libapps.salisbury.edu/status/alert.php?p=app

This URL will contain a JSON object in the format {"message": "[message here]"}. If "message" is an empty string no notice should appear, but if there is something there it should be displayed to the user on the app's first/main screen. This should not be done as a modal alert that the user has to dismiss.


# Search Suggestions

URL: https://libapps.salisbury.edu/search/redirect.php

*This feature is currently in development, so probably shouldn't be implemented in the app until the fall.*

Searches from the main resources search box should be directed to this URL first. It should be sent as a POST request with the following parameters:

- query: the search string the user entered
- s: the source for where the search came from (in this case meaning the Android app, so it should always be "AND")

A JSON object will be returned. If it's an empty object, the search should just be sent on to our search site as is. If an object with content is returned, then the user's search matched a flagged term that the library has identified. Instead of doing a general search, we want to suggest the resources returned in the JSON object.

The object array will have the folllowing format:

[{
    id:   [int],
    label: [string],
    url: [string]
}]

ID is the ID of the suggested resource. Label is the name that the user should be shown. URL is the URL that they should be taken to if they accept the redirection.

Note that there may be multiple objects in teh array if there is more than one recommendation. Some examples you can use: "jstor", "HIST 101", "SC2018.041". These searches can be tried on the library homepage to see how they behave there as well.

