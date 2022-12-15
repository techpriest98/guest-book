create table if not exists feedbacks(
    id serial primary key ,
    author_name varchar not null ,
    feedback varchar not null,
    feedback_date timestamp not null ,
    rating varchar not null
)
