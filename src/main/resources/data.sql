insert ignore into info_pages values (
    random_uuid(), '{}', true
);

insert ignore into info_pages values (
    random_uuid(), '{}', false
);

insert ignore into timeline_stages values
(
    random_uuid(),
    'Registration',
    'Participant and team registrations are open. Once this stage ends, you will no longer be able to enter the competition.',
    '2023-03-01 00:00:00',
    'BLOCK_REGISTRATION'
),
(
    random_uuid(),
    'Development',
    'The main stage of the competition. Once this stage ends, you will no longer be able to submit your solution.',
    '2023-03-10 00:00:00',
    'BLOCK_GITHUB_SUBMISSION'
),
(
    random_uuid(),
    'Presentation',
    'The final stage of the competition where you present your solution. Once this stage ends, the results will be revealed.',
    '2023-03-13 00:00:00',
    'SHOW_RESULTS'
)

