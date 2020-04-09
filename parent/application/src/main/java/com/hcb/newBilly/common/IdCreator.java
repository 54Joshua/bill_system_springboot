package com.hcb.newBilly.common;

import java.util.UUID;

public class IdCreator {
   public static String getNextId(){
       UUID uuid = UUID.randomUUID();
       String uustr = uuid.toString();
       String result = uustr.replace("-", "");
       return result;
   }
}
