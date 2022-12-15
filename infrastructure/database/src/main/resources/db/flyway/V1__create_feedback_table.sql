create table if not exists feedbacks(
    id serial primary key ,
    authorName String not null ,
    feedback String not null,
    feedbackDate timestamp not null ,
    rating integer not null
)
