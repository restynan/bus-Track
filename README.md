# Location Manager Android Project

This Android project provides robust functionality for retrieving and displaying location details using the `LocationManager` and `Geocoder` services. It focuses on handling location updates, fetching geographical information, and visualizing this data on a map with markers.

## Key Features

1. **Location Updates:**
   - Utilizes `LocationManager` to request location updates from the GPS provider.
   - Handles location updates with specified minimum time and distance criteria to ensure efficient and timely data collection.

2. **Geocoding:**
   - Uses the `Geocoder` class to convert latitude and longitude coordinates into human-readable addresses.
   - Retrieves detailed location information, including address, city, state, and country.

3. **Map Integration:**
   - Adds markers to a Google Map at specified coordinates.
   - Displays location details and moves the camera to the marker position.

## Getting Started

### Prerequisites

- Android Studio
- Google Maps API Key

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/location-manager-android.git
2. Open the project in Android Studio.

3. Add your Google Maps API key to the AndroidManifest.xml file

4. Sync the project with Gradle files.
