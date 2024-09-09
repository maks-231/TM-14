FROM postgres:16.4-bookworm

ENV LD_LIBRARY_PATH=/usr/lib/postgresql/16/lib/
ENV EDB_SUBSCRIPTION_TOKEN=CGz0NQolv81IowDZyoyUaPPtqOh7fyzn

RUN apt -y update
#RUN apt -y install postgresql-16-pglogical

RUN apt -y install  curl
RUN curl -1sLf "https://downloads.enterprisedb.com/$EDB_SUBSCRIPTION_TOKEN/postgres_distributed/setup.deb.sh" | bash

RUN apt -y install edb-bdr5-pg16 edb-pgd5-proxy edb-pgd5-cli
RUN echo "*:*:*:postgres:postgres" > /tmp/pgpass.conf
RUN chmod 0600 /tmp/pgpass.conf
ENV PGPASSFILE=/tmp/pgpass.conf

