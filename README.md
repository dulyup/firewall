# firewall

Based on my understanding, the requirements and my implementations are as following:

* All valid rules are stored in a csv file. 
    * Created a constructor, take the csv file path as input.
    * Imported apache csv dependency to read each record in csv file, and store all the rules into memory.

* There may be massive number of rules in the csv file (500k as the baseline)
    * I divided all rules into four sets, based on the combinations of direction and protocol.
    * In each set (HashMap):
        1. Wrote a hash function and get a hashcode based on port and ip.
        2. Used the hashcode as the key, traffic object as the value. 

* Receive a packet with direction, protocol, port and ip, determine if it's valid. Thus, it can be accepted by the firewall.
    * Created a Traffic class with attributes of direction, protocol, port and ip.
    * Override equals function, to compare if values are equal between two Traffic objects, instead of comparing addresses. In the meantime, ensure range comparision.

* Test
    * Created a csv file with thousands of rules.
    * Used JUnit to test hash function and acceptPacket function.
    
* Further optimization
    * Find a better way to read large files to avoid OutOfMemoryError.
