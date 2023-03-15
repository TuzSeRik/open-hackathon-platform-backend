insert ignore into info_pages values (
    random_uuid(), '{' +
                      '"type": "header",' +
                      '"data": {' +
                        '"text": "This is the best hackathon ever created!",' +
                        '"level": 2' +
                      '}' +
                    '}', true
);

insert ignore into info_pages values (
    random_uuid(), '{' +
                      '"type": "header",' +
                      '"data": {' +
                        '"text": "This is the best hackathon ever created!",' +
                        '"level": 2' +
                      '}' +
                    '}', false
);
