# DOWNLOAD="wget"


DOWNLOAD="curl"
mkdir "HTML"
$DOWNLOAD "http://megalotto.pl/wyniki/lotto/losowania-z-roku-2020" > "HTML/2020-wyniki-lotto.html"
$DOWNLOAD "http://megalotto.pl/wyniki/mini-lotto/losowania-z-roku-2020" > "HTML/2020-wyniki-mini-lotto.html"



