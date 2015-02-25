BASEDIR=$(dirname $0)

REPORT='report'
REPORT_DIR="$BASEDIR/reports"
LIB_DIR="$BASEDIR/lib"

mkdir -p $REPORT_DIR

case $1 in
    'gui') 
	java -cp $LIB_DIR/jdepend-2.9.1.jar:$LIB_DIR jdepend.swingui.JDepend  $BASEDIR/.. 
	;;
    'xml')
	java -cp $LIB_DIR/jdepend-2.9.1.jar:$LIB_DIR jdepend.xmlui.JDepend  $BASEDIR/.. 
	;;
    "$REPORT")
	java -cp $LIB_DIR/jdepend-2.9.1.jar:$LIB_DIR jdepend.xmlui.JDepend  $BASEDIR/.. > $REPORT_DIR/jdepend.xml
	xsltproc $LIB_DIR/jdepend2dot.xsl $REPORT_DIR/jdepend.xml > $REPORT_DIR/jdepend.dot
	dot -Tpng $REPORT_DIR/jdepend.dot > $REPORT_DIR/jdepend.png
	xsltproc $LIB_DIR/jdepend2html_img.xsl $REPORT_DIR/jdepend.xml > $REPORT_DIR/jdepend.html 
	xsltproc $LIB_DIR/jdepend2graphml.xsl $REPORT_DIR/jdepend.xml > $REPORT_DIR/jdepend.graphml 
	sensible-browser $REPORT_DIR/jdepend.html
	;;
esac 


