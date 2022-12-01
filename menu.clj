(ns menu)
 
(defn printMenu []
  (def mapMenu
    {:1 "Display Customer Table" :2 "Display Product Table" :3 "Display Sales Table" :4 "Total Sales for Customer" :5 "Total Count for Product" :6 "Exit"
     }) 
  (println "1." get mapMenu :1)
  (println "2." get mapMenu :2)
  (println "3." get mapMenu :3)
  (println "4." get mapMenu :4)
  (println "5." get mapMenu :5)
  (println "6." get mapMenu :6)
  )
(printMenu)
