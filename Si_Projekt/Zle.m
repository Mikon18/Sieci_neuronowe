fileID = fopen('slowa2.txt','r');
X = fscanf(fileID,'%d',[8 Inf])
fileID = fopen('slowa_zmodyfikowane.txt','r');
X2 = fscanf(fileID,'%1d',[8 Inf])
fileID = fopen('indeksy.txt','r');
Y = fscanf(fileID,'%d',[1 Inf]);
net = newff( minmax(X), [8 1], {'tansig' 'purelin'}, 'trainbr');
net.trainParam.epochs = 2000;
net = train(net,X,Y);
Y2 = net(X2);
Y2 = round(Y2);
c = 0
for n = 1:100
        c = c + abs(Y(n) - Y2(n));
end
c/100
        
        