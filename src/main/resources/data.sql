insert ignore into info_pages values (
    random_uuid(), '{"blocks": ['||
                        '{' ||
                          '"type": "header",' ||
                          '"data": {' ||
                            '"text": "This is the best hackathon ever created!",' ||
                            '"level": 2' ||
                          '}' ||
                        '}'||
                    ']}', true
);

insert ignore into info_pages values (
    random_uuid(), '{"blocks": ['||
                        '{' ||
                            '"type": "header",' ||
                            '"data": {' ||
                                '"text": "Lets get started!",' ||
                                '"level": 2' ||
                            '}' ||
                        '}'||
                    ']}', false
);
