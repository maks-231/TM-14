https://gist.github.com/RafaelMCarvalho/4d5cce26a45d1d5f87d0643a699d41c2

https://hevodata.com/learn/postgresql-multi-master-replication/

docker exec -it 81b2696507c5 /bin/bash

Step 1: Install BDR and pglogical plugin.
Dockerfile:
FROM postgres:16.4-bookworm
RUN apt -y update && apt install -y postgresql-16-pglogical

cmd:
docker build -t postgres:16.4-bookworm-pglogica .

Step 2: Configure postgresql.conf to these values.
wal_level = logical
shared_preload_libraries= ‘pg_logical, bdr’
track_commit_timestamp= ‘on’  # This is necessary for conflict resolution.

Step 3: Create a user with superuser privileges to manage BDR connectivity.
CREATE USER replication_user WITH SUPERUSER REPLICATION PASSWORD 'replication_password';

Step 4: Change the pg_hba.conf file by adding these lines.
host   all         bdr   10.20.30.40/24   md5
host   replication bdr   10.20.30.40/24   md5

put http://localhost:8765/customer-details/update-customer/2

body {
    "firstName": "Frank",
    "lastName": "Sinatra",
    "birthDate": "2024-01-15"
}