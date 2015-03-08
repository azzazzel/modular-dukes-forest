BASEDIR=$(dirname $0)

TAGS=(DEMO_START_POINT DOMAIN_MODEL USECASES PERSISTENCE WEBAPP RESTRUCTURE MICROSERVICE BUNDLES OSGI_SERVICE OSGI_REST PORTLET DOSGI)
TAGS_COUNT=${#TAGS[@]}
FILE=/tmp/.demo.status
CURRENT=$(cat $FILE || echo '1')

case $1 in
    'start') 
	CURRENT=0;	
	;;
    'back')
	if [[ $CURRENT > 0 ]]; then 
		CURRENT=$(($CURRENT-1));	
	fi
	;;
    *)
	if [[ $CURRENT -ge $TAGS_COUNT ]]; then 
		echo "That's all folks!"
		exit	
	fi

	NEXT=$(($CURRENT+1)) 
	clear
	echo "Commits between ${TAGS[$CURRENT]} and ${TAGS[$NEXT]} tags:"
	git log --pretty=oneline ${TAGS[$CURRENT]}...${TAGS[$NEXT]}
	read -s
	git difftool -d ${TAGS[$CURRENT]}...${TAGS[$NEXT]}
	CURRENT=$NEXT;	
	;;
esac 

git checkout ${TAGS[$CURRENT]}
git clean -d -f
echo $CURRENT > $FILE


