# LibCal API Documentation

URL: https://salisbury.libcal.com/admin/api/1.1

Used for library hours and study room reservations

- Study Room Reservations make use of the "Spaces" API (not the "Room Bookings" API).
- Library Hours uses the "Hours" API.
- Requires a username and password to access
    - Username: sdisharoon1@gulls.salisbury.edu
    - Password: (set by Samuel)


# LibraryH3lp (Chat)


URL: https://dev.libraryh3lp.com/

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

