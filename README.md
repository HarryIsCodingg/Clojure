# Clojure
This program will load data from a series of three disk files. This data will then form your
Sales database. Each table will have a “schema” that indicates the fields inside. So DB will look
like this:

cust.txt: This is the data for the customer table. The schema is
<custID, name, address, phoneNumber>
An example of the cust.txt disk file might be:
1|John Smith|123 Here Street|456-4567
2|Sue Jones|43 Rose Court Street|345-7867
3|Fan Yuhong|165 Happy Lane|345-4533

prod.txt: This is the data for the product table. The schema is
<prodID, itemDescription, unitCost>
An example of the prod.txt disk file might be:
1|shoes|14.96
2|milk|1.98
3|jam|2.99
4|gum|1.25
5|eggs|2.98
6|jacket|42.99

sales.txt: This is the data for the main sales table. The schema is
<salesID, custID, prodID, itemCount>
An example of the sales.txt disk file might be:
1|1|1|3
2|2|2|3
3|2|1|1
4|3|3|4
