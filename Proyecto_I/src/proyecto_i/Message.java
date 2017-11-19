/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

/**
 *
 * @author sebas
 */
public class Message {
        private int GroupNumber;
        private String Emitter;
        private String Receptor;
        private String Date;
        private String Message;
      
        public Message(int groupnumber,String emitter,String receptor,String date, String message)
        {
            GroupNumber = groupnumber;
            Emitter = emitter;
            Receptor = receptor;
            Date = date;
            Message = message;
        }
        public int getGroupNumber() {
            return GroupNumber;
        }
        public String getEmitter() {
            return Emitter;
        }
        public String getReceptor() {
            return Receptor;
        }
        public String getDate() {
            return Date;
        }
        public String getMessage() {
            return Message;
        }
        
        
}
