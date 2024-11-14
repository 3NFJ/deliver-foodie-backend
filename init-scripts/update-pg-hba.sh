#!/bin/bash
cat > "$PGDATA/pg_hba.conf" << EOL
# TYPE  DATABASE        USER            ADDRESS                 METHOD
local   all            all                                     trust
host    all            all             127.0.0.1/32            md5
host    all            all             ::1/128                 md5
host    all            all             0.0.0.0/0               md5
EOL

