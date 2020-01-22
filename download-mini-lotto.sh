# DOWNLOAD="wget"
DOWNLOAD="curl"
REP="MINIHTML"
mkdir $REP
for i in {1981..2020}
do
  $DOWNLOAD "http://megalotto.pl/wyniki/mini-lotto/losowania-z-roku-$i" > "$REP/$i-wyniki-mini-lotto.html"
done
