function fow(x,n) {
    return n ? x*fow(x,n-1) : x;
}
alert(fow(1,100));