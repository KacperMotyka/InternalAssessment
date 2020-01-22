# DOWNLOAD="wget"
DOWNLOAD="curl"
REP="LOTTOHTML"
mkdir $REP
for i in {1957..2020}
do
  $DOWNLOAD "http://megalotto.pl/wyniki/lotto/losowania-z-roku-$i" > "$REP/$i-wyniki-lotto.html"
done
