package com.filestask

class FilesTask{
    /*
    This function maps key value pairs in the files.
    Here '=' is treated as delimiter
     */
    static def fileKeyValues(filepath){
        //'data' map is used for storing key, value pairs
        def data = [:]
        try{
            File myFile1=new File(filepath)
            // this block of code reads line by line and split the line with '=' as delimiter.
            // The split strings are now collected and added to 'data' map
            myFile1.eachLine {line->
                data.put(line.split('=').collect {it })
            }
        }
        catch (Exception e){
            print "Exception is caught in fileKeyValues method\n"
        }
        // returns 'data' map
        println "Returning data"
        data
    }

    /*
    This function takes filepath, old value and new value as parameters.
    It modifies the old value with new value.
     */
    static def modifyFile(filepath,key,oldValue,newValue){
        try{
            def myFile = new File(filepath)
            def fileText = myFile.text
            fileText = (fileText =~ key+"="+oldValue).replaceFirst(key+"="+newValue)
            myFile.write(fileText)
        }
        catch (Exception e){
            print "Exception is caught in modifyFile method\n"
        }
    }
    static void main(String[] args){
        String filePath1="test.txt"
        String filePath2="test1.txt"
        def keyValue1 = [:]
        keyValue1 = fileKeyValues(filePath1)
        print "\nKey-Value pair1 from File1 before modification\t"
        print keyValue1
        def keyValue2 = [:]
        keyValue2 = fileKeyValues(filePath2)
        print "\nKey-Value pair2 from File2 before modification\t"
        print keyValue2
        try{
            // For traversing the two key-value maps and modifying the values with correct data
            for (Map.Entry<String, String> entry1 : keyValue1.entrySet()){
                String key = entry1.getKey()
                String value1 = entry1.getValue()
                String value2 = keyValue2.get(key)
                // When value of a key from second file is null, then replace with value of key in second file with value of key first file
                if(!value1.contentEquals(value2) && !value1.contentEquals("null") && value2.contentEquals("null")){
                    modifyFile(filePath2,key,value2,value1)
                }
                // When both the values of a key are not null, and different then replace with value of key in 1st file with value of key from second file
                else if(!value1.contentEquals(value2) && !value1.contentEquals("null") && !value2.contentEquals("null")){
                    modifyFile(filePath1,key,value1,value2)
                }
                // When value of a key from first file is null, then replace with value of key in 1st file from second file
                else if(!value1.contentEquals(value2) && value1.contentEquals("null") && !value2.contentEquals("null")){
                    modifyFile(filePath1,key,value1,value2)
                }
                else if(value1.contentEquals("null") && value2.contentEquals("null")){
                    print "\nBoth Values of the Key are null\n"
                }
            }
        }
        catch (Exception e){
            print "Exception is caught when executing modifyFile operations\n"
        }
        keyValue1 = fileKeyValues(filePath1)
        print "\nKey-Value pair1 from File 1 after modification\t"
        print keyValue1
        keyValue2 = fileKeyValues(filePath2)
        print "\nKey-Value pair2 from File 2 after modification\t"
        print keyValue2
    }
}
