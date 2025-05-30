curl -X POST 'http://localhost:8080/api/upload' -H 'Content-Type: application/json'
 -d '{
        "id": "517",
        "imp": [
            {
                "id": "345",
                "banner": {
                    "w": 1080,
                    "h": 720,
                    "pos": 1,
                    "btype": [
                        4
                    ],
                    "battr": [
                        14
                    ],
                    "api": [
                        3
                    ]
                },
                "instl": 0,
                "tagid": "agltb3B1Yi1pbmNyDQsSBFNpdGUY7fD0FAw",
                "bidfloor": 0.5
            }
        ],
        "app": {
            "id": "agltb3B1Yi1pbmNyDAsSA0FwcBiJkfIUDA",
            "name": "Yahoo Weather",
            "cat": [
                "weather",
                "IAB15",
                "IAB15-10"
            ],
            "ver": "1.0.2",
            "bundle": "628677149",
            "publisher": {
                "id": "agltb3B1Yi1pbmNyDAsSA0FwcBiJkfTUCV",
                "name": "yahoo",
                "domain": "www.yahoo.com"
            },
            "storeurl": "https://itunes.apple.com/id628677149"
        },
        "device": {
            "dnt": 0,
            "ua": "Mozilla/5.0 (iPhone; CPU iPhone OS 6_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A334 Safari/7534.48.3",
            "ip": "123.145.167.189",
            "geo": {
                "country": "USA",
                "lat": 42.012346,
                "lon": -122.12345,
                "city": "Los Angeles",
                "metro": "803",
                "region": "CA",
                "zip": "90049"
            },
            "dpidsha1": "AA000DFE74168477C70D291f574D344790E0BB11",
            "dpidmd5": "AA003EABFB29E6F759F3BDAB34E50BB11",
            "carrier": "310-410",
            "language": "en",
            "make": "Apple",
            "model": "iPhone",
            "os": "iOS",
            "osv": "6.1",
            "js": 1,
            "connectiontype": 3,
            "devicetype": 1
        },
        "user": {
            "id": "ffffffd5135596709273b3a1a07e466ea2bf4fff",
            "yob": "1984",
            "gender": "M"
        },
        "at": 2,
        "bcat": [
            "IAB25",
            "IAB7-39",
            "IAB8-18",
            "IAB8-5",
            "IAB9-9"
        ],
        "badv": [
            "apple.com",
            "go-text.me",
            "heywire.com"
        ]
    }'


curl -X POST 'http://localhost:8080/api/upload' -H 'Content-Type: application/json'
 -d '{
         "id": "IxexyLDIIk",
         "imp": [
             {
                 "id": "1",
                 "banner": {
                     "w": 728,
                     "h": 90,
                     "pos": 1,
                     "btype": [
                         4
                     ],
                     "battr": [
                         14
                     ],
                     "api": [
                         3
                     ]
                 },
                 "instl": 0,
                 "tagid": "agltb3B1Yi1pbmNyDQsSBFNpdGUY7fD0FAw",
                 "bidfloor": 0.5
             }
         ],
         "app": {
             "id": "agltb3B1Yi1pbmNyDAsSA0FwcBiJkfIUDA",
             "name": "Yahoo Weather",
             "cat": [
                 "weather",
                 "IAB15",
                 "IAB15-10"
             ],
             "ver": "1.0.2",
             "bundle": "628677149",
             "publisher": {
                 "id": "agltb3B1Yi1pbmNyDAsSA0FwcBiJkfTUCV",
                 "name": "yahoo",
                 "domain": "www.yahoo.com"
             },
             "storeurl": "https://itunes.apple.com/id628677149"
         },
         "device": {
             "dnt": 0,
             "ua": "Mozilla/5.0 (iPhone; CPU iPhone OS 6_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A334 Safari/7534.48.3",
             "ip": "123.145.167.189",
             "geo": {
                 "country": "USA",
                 "lat": 35.012345,
                 "lon": -115.12345,
                 "city": "Los Angeles",
                 "metro": "803",
                 "region": "CA",
                 "zip": "90049"
             },
             "dpidsha1": "AA000DFE74168477C70D291f574D344790E0BB11",
             "dpidmd5": "AA003EABFB29E6F759F3BDAB34E50BB11",
             "carrier": "310-410",
             "language": "en",
             "make": "Apple",
             "model": "iPhone",
             "os": "iOS",
             "osv": "6.1",
             "js": 1,
             "connectiontype": 3,
             "devicetype": 1
         },
         "user": {
             "id": "ffffffd5135596709273b3a1a07e466ea2bf4fff",
             "yob": "1984",
             "gender": "M"
         },
         "at": 2,
         "bcat": [
             "IAB25",
             "IAB7-39",
             "IAB8-18",
             "IAB8-5",
             "IAB9-9"
         ],
         "badv": [
             "apple.com",
             "go-text.me",
             "heywire.com"
         ]
     }'

curl -X POST 'http://localhost:8080/api/upload' -H 'Content-Type: application/json'
 -d ''