MYSQLPWD=...
NOW=$(date +"%Y%m%d-%H%M")
BACKUP_DIR=/backups/mysql
SQL_FILE=$BACKUP_DIR/db-all-$NOW.sql
ZIP_FILE=$BACKUP_DIR/db-all-$NOW.zip

mysqldump --all-databases -u root -p$MYSQLPWD > $SQL_FILE
zip -j $ZIP_FILE $SQL_FILE
rm $SQL_FILE