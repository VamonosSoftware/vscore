NOW=$(date +"%Y%m%d-%H%M")
BACKUP_DIR=/Users/paulrule/backups/websites/for/me

mkdir -p $BACKUP_DIR

tar -zcvf $BACKUP_DIR/site1-$NOW.tar.gz /Users/paulrule/Sites/images
tar -zcvf $BACKUP_DIR/site2-$NOW.tar.gz /Users/paulrule/Sites/indigo
