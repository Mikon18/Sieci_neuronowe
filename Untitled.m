fileID = fopen('slowa.txt','r');
X = fscanf(fileID,'%1d',[64 Inf])
fileID = fopen('slowa_modyfikowane.txt','r');
X2 = fscanf(fileID,'%1d',[64 Inf])
fileID = fopen('indeksy.txt','r');
Y = fscanf(fileID,'%d',[1 Inf]);
net = newff( minmax(X), [16 1], {'satlins' 'purelin'}, 'trainbr');
net.trainParam.epochs = 500;
net = train(net,X,Y);
y2 = net(X2);
c = 0
for n = 1:100
        c = c + abs(Y(n) - y2(n));
end
c/100
        
        