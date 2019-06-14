tempdir="/home/teachers/t2015025/byod"
class="java19"
rm -rf ~/public_html/progjava/${class}
mkdir ${tempdir} && git -C ${tempdir} init
git -C ${tempdir} config core.sparsecheckout true
git -C ${tempdir} remote add origin https://github.com/igakilab/byod.zip.git
echo /java_src/ > ${tempdir}/.git/info/sparse-checkout
git -C ${tempdir} pull origin master
mv ${tempdir}/java_src/ ~/public_html/progjava/
mv ~/public_html/progjava/java_src/ ~/public_html/progjava/${class}/
rm -rf ${tempdir}

# make dir
mkdir -p ~/public_html/progjava/${class}/bin/
for i in `seq -f %02g 1 14`
do
  mkdir -p ~/public_html/progjava/${class}/lec${i}
done
for j in `seq 1 3`
do
  mkdir -p ~/public_html/progjava/${class}/test/test${j}
done

chmod 755 -R ~/public_html/progjava/${class}/