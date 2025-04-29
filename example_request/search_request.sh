curl -X POST 'localhost:8080/api/search' \
--header 'Content-Type: application/json' \
--data '{
    "imp": [
        {
            "id": "345"
        }
    ],
    "device":{
        "geo":{
            "lat": 42.012346,
            "lon": -122.12345
        }
    }
}'