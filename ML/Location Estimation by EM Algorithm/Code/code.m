load loc.txt;

rowtotal=size(loc,1);
coltotal=size(loc,2);

prompt = 'Enter no of ships:';
k = input(prompt);
%disp(k);
covariancecell=cell(k,1);
meu=zeros(k,coltotal);
theta=rand(1,k);
sumval=sum(theta);
for i=1:k
    temp=randi(rowtotal);
    %disp(temp);
    meu(i,:)=loc(temp,:);
    covariancecell{i}=cov(loc);
    theta(1,i)=theta(1,i)/sumval;
    
end

%calcloglikelihood


sum2=0.0;
for i=1:rowtotal
    sumval=0.0;
    for j=1:k
        n=mvnpdf(loc(i,:),meu(j,:),covariancecell{j});
        sumval=sumval+(theta(1,j)*n);
    end
    sum2=sum2+log(sumval);
end

tempn=sum2;
count=0;
while(1)
    
    %E-step

    piarray=zeros(rowtotal,k);
    for i=1:rowtotal
        for j=1:k
            n=mvnpdf(loc(i,:),meu(j,:),covariancecell{j});
            sum1=theta(1,j)*n;
            sum2=0.0;
            for k1=1:k
                n=mvnpdf(loc(i,:),meu(k1,:),covariancecell{k1});
                 sum2=sum2+(theta(1,k1)*n);
            end
            piarray(i,j)=sum1/sum2;
        end
    end

    %M-step

    for k1=1:k
        sum1=0.0;
        sum2=0.0;
        sum3=0.0;
        for i=1:rowtotal
             sum1=sum1+(piarray(i,k1)*loc(i,:));
             sum2=sum2+piarray(i,k1);

             temp=(loc(i,:))-(meu(k1,:));
             temptranspose=transpose(temp);
             sum3=sum3+(piarray(i,k1)*temptranspose*temp);



        end
        meu(k1,:)=sum1/sum2;
        theta(1,k1)=sum2/rowtotal;
        covariancecell{k1}=sum3/sum2;
    end
    
    %calcloglikelihood


    sumval2=0.0;
    for i=1:rowtotal
        sumval=0.0;
        for j=1:k
            n=mvnpdf(loc(i,:),meu(j,:),covariancecell{j});
            sumval=sumval+(theta(1,j)*n);
        end
        sumval2=sumval2+log(sumval);
    end
    if((sumval2-tempn)==0)
        break;
    else
        %disp(sumval2-tempn);
        tempn=sumval2;
        %disp('gggg');
        %disp(count);
        count=count+1;
    end
end
%disp('gggg');
for i=1:k
    disp(meu(i,:));
    disp(covariancecell{i});
end
%disp(size(loc));   
scatter(loc(:,1),loc(:,2));     
hold on;
scatter(meu(:,1),meu(:,2),'r','Filled');
hold off;

