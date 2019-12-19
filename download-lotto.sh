# DOWNLOAD="wget"
DOWNLOAD="curl"
REP="LOTTO-HTML"
mkdir $REP
for i in {1957..2019}
do
  $DOWNLOAD "http://megalotto.pl/wyniki/lotto/losowania-z-roku-$i" > "$REP/$i-wyniki-lotto.html"
done