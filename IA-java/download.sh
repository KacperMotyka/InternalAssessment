DOWNLOAD="curl"

# mkdir "HTML"
# $DOWNLOAD "http://megalotto.pl/wyniki/lotto/losowania-z-roku-2020" > "HTML/2020-wyniki-lotto.html"
# $DOWNLOAD "http://megalotto.pl/wyniki/mini-lotto/losowania-z-roku-2020" > "HTML/2020-wyniki-mini-lotto.html"
$DOWNLOAD http://www.mbnet.com.pl/dl.txt > lotto-history.txt
$DOWNLOAD http://www.mbnet.com.pl/el.txt > mini-lotto-history.txt
$DOWNLOAD http://www.mbnet.com.pl/ml.txt > multi-lotto-history.txt

#DOWNLOAD="wget"
#$DOWNLOAD http://www.mbnet.com.pl/dl.txt
#$DOWNLOAD http://www.mbnet.com.pl/el.txt
#$DOWNLOAD http://www.mbnet.com.pl/ml.txt
#mv dl.txt lotto-history.txt
#mv el.txt mini-lotto-history.txt
#mv ml.txt multi-lotto-history.txt