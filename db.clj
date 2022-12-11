(ns db)


(defn loadData []
  (def customerFile
    (slurp "cust.txt"))

  (def customerDataLines
    (clojure.string/split-lines customerFile))
  (def productFile
    (slurp "prod.txt"))
  (def productDataLines
    (clojure.string/split-lines productFile))
  (def salesFile
    (slurp "sales.txt"))
  (def salesDataLines
    (clojure.string/split-lines salesFile)))



(defn printCustData [size index]
  (def dataEntry
    (clojure.string/split (get customerDataLines (- index 1)) #"\|"))
  (if (= size index)
    (do
      (println index ":" [(get dataEntry 1) "," (get dataEntry 2) "," (get dataEntry 3)]))
    (do
      (println index ":" [(get dataEntry 1) "," (get dataEntry 2) "," (get dataEntry 3)])
      (printCustData size (+ index 1)))))

(defn printProductData [size index]
  (def dataEntry
    (clojure.string/split (get productDataLines (- index 1)) #"\|"))
  (if (= size index)
    (do
      (println index ":" [(get dataEntry 1) "," (get dataEntry 2)]))
    (do
      (println index ":" [(get dataEntry 1) "," (get dataEntry 2)])
      (printProductData size (+ index 1)))))

(defn salesData [size index]
  (def dataEntry
    (clojure.string/split (get salesDataLines (- index 1)) #"\|"))
  (def customerDataLinesSplit
    (clojure.string/split (get customerDataLines (- (Integer/parseInt (get dataEntry 1)) 1)) #"\|"))
  (def salesDataSplit
    (clojure.string/split (get productDataLines (- (Integer/parseInt (get dataEntry 2)) 1)) #"\|"))
  (if (= size index)
    (do
      (println index ":"  [(get customerDataLinesSplit 1) "," (get salesDataSplit 1) "," (get dataEntry 3)]))
    (do
      (println index ":" [(get customerDataLinesSplit 1) "," (get salesDataSplit 1) "," (get dataEntry 3)])
      (salesData size (+ index 1)))))


(defn search [custName size index]
  (def customerInFile
    (clojure.string/split (get customerDataLines index) #"\|"))
  (if (= size 1)
    (do
      (if (= (get customerInFile 1) custName)
        (+ index 1)
        nil))
    (do
      (if (= (get customerInFile 1) custName)
        (+ index 1)
        (search custName (- size 1) (+ index 1))))))


(defn calculateSales [custName customerId size]

  (def saleData
    (clojure.string/split (get salesDataLines (- size 1)) #"\|"))

  (def custId
    (Integer/parseInt (get saleData 1)))

  (def numberOfItems
    (Integer/parseInt (get saleData 3)))

  (def productId
    (Integer/parseInt (get saleData 2)))

  (def price
    (Float/parseFloat (get (clojure.string/split (get productDataLines (- productId 1)) #"\|") 2)))

  (if (= size 1)
    (do

      (if (= customerId custId)
        (* price numberOfItems)
        0.0))
    (do
      (if (= customerId custId)
        (do
          (+ (* price numberOfItems) (calculateSales custName customerId (- size 1))))
        (do
          (+ 0.0 (calculateSales custName customerId (- size 1))))))))

(defn searchProduct [prodName size index]
  (def productInFile
    (clojure.string/split (get productDataLines index) #"\|"))

  (if (= size 1)
    (do
      (if (= (get productInFile 1) prodName)
        (+ index 1)
        0))
    (do
      (if (= (get productInFile 1) prodName)
        (+ index 1)
        (searchProduct prodName (- size 1) (+ index 1))))))

(defn calculateProducts [prodId size]

  (def saleData
    (clojure.string/split (get salesDataLines (- size 1)) #"\|"))

  (def numberOfItems
    (Integer/parseInt (get saleData 3)))

  (def productId
    (Integer/parseInt (get saleData 2)))

  (if (= size 1)
    (do
      (if (= prodId productId)
        numberOfItems
        0))
    (do
      (if (= prodId productId)
        (do
          (+ numberOfItems (calculateProducts prodId (- size 1))))
        (do
          (+ 0 (calculateProducts prodId (- size 1))))))))

(defn getData [option]

  (if (= option 1)
    (do
      (println "\n*** Displaying Customer Table ***")
      (println "-------------------------------\n")
      (printCustData (count customerDataLines) 1)))

  (if (= option 2)
    (do
      (println "\n*** Displaying Product Table ***")
      (println "-------------------------------\n")
      (printProductData (count productDataLines) 1)))
  (if (= option 3)
    (do
      (println "\n*** Displaying Sales Table ***")
      (println "-------------------------------\n")
      (salesData (count salesDataLines) 1)))
  (if (= option 4)
    (do
      (println "Please enter the customer name: ")
      (flush)
      (def custName (read-line))
      (def isCustomerFound (search custName (count customerDataLines) 0))
      (if (nil? isCustomerFound)
        (do
          (println "\nThe customer with name" custName "is not found in the database! "))
        (do
          (println "\nTotal value of the purchase for " custName "\n")
          (println custName ":" (format "%.2f" (calculateSales custName isCustomerFound (count salesDataLines))))))))
  (if (= option 5)
    (do
      (println "Please enter the product name: ")
      (flush)
      (def prodName (read-line))
      (def isProductFound (searchProduct prodName (count productDataLines) 0))
      (if (= isProductFound 0)
        (do
          (println "\nThe product with name" prodName "is not found in the database! "))
        (do
          (println "\nTotal number of" prodName "sold are:\n")
          (println prodName ":" (calculateProducts isProductFound (count salesDataLines))))))))





