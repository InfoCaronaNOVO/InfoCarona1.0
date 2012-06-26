package ufcg.si1.infoCarona.view;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class ExceptionSerialized_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  public static void deserialize(SerializationStreamReader streamReader, ufcg.si1.infoCarona.view.ExceptionSerialized instance) throws SerializationException {
    
    com.google.gwt.user.client.rpc.core.java.lang.Exception_FieldSerializer.deserialize(streamReader, instance);
  }
  
  public static ufcg.si1.infoCarona.view.ExceptionSerialized instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new ufcg.si1.infoCarona.view.ExceptionSerialized();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, ufcg.si1.infoCarona.view.ExceptionSerialized instance) throws SerializationException {
    
    com.google.gwt.user.client.rpc.core.java.lang.Exception_FieldSerializer.serialize(streamWriter, instance);
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return ufcg.si1.infoCarona.view.ExceptionSerialized_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    ufcg.si1.infoCarona.view.ExceptionSerialized_FieldSerializer.deserialize(reader, (ufcg.si1.infoCarona.view.ExceptionSerialized)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    ufcg.si1.infoCarona.view.ExceptionSerialized_FieldSerializer.serialize(writer, (ufcg.si1.infoCarona.view.ExceptionSerialized)object);
  }
  
}
