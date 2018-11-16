tempdir="/home/teachers/t2015025/byod"
rm -rf ~/public_html/progjava/local/bin/*
mkdir ${tempdir} && git -C ${tempdir} init
git -C ${tempdir} config core.sparsecheckout true
git -C ${tempdir} remote add origin https://github.com/igakilab/byod.zip.git
echo /PortableGit/usr/local/bin/ > ${tempdir}/.git/info/sparse-checkout
git -C ${tempdir} pull origin master
mv ${tempdir}/PortableGit/usr/local/bin/ ~/public_html/progjava/local/
rm -rf ${tempdir}
