// ORM class for table 'accident'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Sun May 07 12:33:00 UTC 2023
// For connector: org.apache.sqoop.manager.PostgresqlManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import org.apache.sqoop.lib.JdbcWritableBridge;
import org.apache.sqoop.lib.DelimiterSet;
import org.apache.sqoop.lib.FieldFormatter;
import org.apache.sqoop.lib.RecordParser;
import org.apache.sqoop.lib.BooleanParser;
import org.apache.sqoop.lib.BlobRef;
import org.apache.sqoop.lib.ClobRef;
import org.apache.sqoop.lib.LargeObjectLoader;
import org.apache.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class accident extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("accident_index", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.accident_index = (Integer)value;
      }
    });
    setters.put("lng", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.lng = (Double)value;
      }
    });
    setters.put("lat", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.lat = (Double)value;
      }
    });
    setters.put("date", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.date = (java.sql.Date)value;
      }
    });
    setters.put("district", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.district = (Integer)value;
      }
    });
    setters.put("road_c1", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.road_c1 = (Integer)value;
      }
    });
    setters.put("road_n1", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.road_n1 = (Integer)value;
      }
    });
    setters.put("road_type", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.road_type = (Integer)value;
      }
    });
    setters.put("speed_limit", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.speed_limit = (Integer)value;
      }
    });
    setters.put("junc_detail", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.junc_detail = (Integer)value;
      }
    });
    setters.put("junc_control", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.junc_control = (Integer)value;
      }
    });
    setters.put("road_c2", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.road_c2 = (Integer)value;
      }
    });
    setters.put("road_n2", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.road_n2 = (Integer)value;
      }
    });
    setters.put("cross_control", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.cross_control = (Integer)value;
      }
    });
    setters.put("cross_facilities", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.cross_facilities = (Integer)value;
      }
    });
    setters.put("light", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.light = (Integer)value;
      }
    });
    setters.put("weather", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.weather = (Integer)value;
      }
    });
    setters.put("road_surface", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.road_surface = (Integer)value;
      }
    });
    setters.put("special_conds", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.special_conds = (Integer)value;
      }
    });
    setters.put("hazards", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.hazards = (Integer)value;
      }
    });
    setters.put("area_type", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        accident.this.area_type = (Integer)value;
      }
    });
  }
  public accident() {
    init0();
  }
  private Integer accident_index;
  public Integer get_accident_index() {
    return accident_index;
  }
  public void set_accident_index(Integer accident_index) {
    this.accident_index = accident_index;
  }
  public accident with_accident_index(Integer accident_index) {
    this.accident_index = accident_index;
    return this;
  }
  private Double lng;
  public Double get_lng() {
    return lng;
  }
  public void set_lng(Double lng) {
    this.lng = lng;
  }
  public accident with_lng(Double lng) {
    this.lng = lng;
    return this;
  }
  private Double lat;
  public Double get_lat() {
    return lat;
  }
  public void set_lat(Double lat) {
    this.lat = lat;
  }
  public accident with_lat(Double lat) {
    this.lat = lat;
    return this;
  }
  private java.sql.Date date;
  public java.sql.Date get_date() {
    return date;
  }
  public void set_date(java.sql.Date date) {
    this.date = date;
  }
  public accident with_date(java.sql.Date date) {
    this.date = date;
    return this;
  }
  private Integer district;
  public Integer get_district() {
    return district;
  }
  public void set_district(Integer district) {
    this.district = district;
  }
  public accident with_district(Integer district) {
    this.district = district;
    return this;
  }
  private Integer road_c1;
  public Integer get_road_c1() {
    return road_c1;
  }
  public void set_road_c1(Integer road_c1) {
    this.road_c1 = road_c1;
  }
  public accident with_road_c1(Integer road_c1) {
    this.road_c1 = road_c1;
    return this;
  }
  private Integer road_n1;
  public Integer get_road_n1() {
    return road_n1;
  }
  public void set_road_n1(Integer road_n1) {
    this.road_n1 = road_n1;
  }
  public accident with_road_n1(Integer road_n1) {
    this.road_n1 = road_n1;
    return this;
  }
  private Integer road_type;
  public Integer get_road_type() {
    return road_type;
  }
  public void set_road_type(Integer road_type) {
    this.road_type = road_type;
  }
  public accident with_road_type(Integer road_type) {
    this.road_type = road_type;
    return this;
  }
  private Integer speed_limit;
  public Integer get_speed_limit() {
    return speed_limit;
  }
  public void set_speed_limit(Integer speed_limit) {
    this.speed_limit = speed_limit;
  }
  public accident with_speed_limit(Integer speed_limit) {
    this.speed_limit = speed_limit;
    return this;
  }
  private Integer junc_detail;
  public Integer get_junc_detail() {
    return junc_detail;
  }
  public void set_junc_detail(Integer junc_detail) {
    this.junc_detail = junc_detail;
  }
  public accident with_junc_detail(Integer junc_detail) {
    this.junc_detail = junc_detail;
    return this;
  }
  private Integer junc_control;
  public Integer get_junc_control() {
    return junc_control;
  }
  public void set_junc_control(Integer junc_control) {
    this.junc_control = junc_control;
  }
  public accident with_junc_control(Integer junc_control) {
    this.junc_control = junc_control;
    return this;
  }
  private Integer road_c2;
  public Integer get_road_c2() {
    return road_c2;
  }
  public void set_road_c2(Integer road_c2) {
    this.road_c2 = road_c2;
  }
  public accident with_road_c2(Integer road_c2) {
    this.road_c2 = road_c2;
    return this;
  }
  private Integer road_n2;
  public Integer get_road_n2() {
    return road_n2;
  }
  public void set_road_n2(Integer road_n2) {
    this.road_n2 = road_n2;
  }
  public accident with_road_n2(Integer road_n2) {
    this.road_n2 = road_n2;
    return this;
  }
  private Integer cross_control;
  public Integer get_cross_control() {
    return cross_control;
  }
  public void set_cross_control(Integer cross_control) {
    this.cross_control = cross_control;
  }
  public accident with_cross_control(Integer cross_control) {
    this.cross_control = cross_control;
    return this;
  }
  private Integer cross_facilities;
  public Integer get_cross_facilities() {
    return cross_facilities;
  }
  public void set_cross_facilities(Integer cross_facilities) {
    this.cross_facilities = cross_facilities;
  }
  public accident with_cross_facilities(Integer cross_facilities) {
    this.cross_facilities = cross_facilities;
    return this;
  }
  private Integer light;
  public Integer get_light() {
    return light;
  }
  public void set_light(Integer light) {
    this.light = light;
  }
  public accident with_light(Integer light) {
    this.light = light;
    return this;
  }
  private Integer weather;
  public Integer get_weather() {
    return weather;
  }
  public void set_weather(Integer weather) {
    this.weather = weather;
  }
  public accident with_weather(Integer weather) {
    this.weather = weather;
    return this;
  }
  private Integer road_surface;
  public Integer get_road_surface() {
    return road_surface;
  }
  public void set_road_surface(Integer road_surface) {
    this.road_surface = road_surface;
  }
  public accident with_road_surface(Integer road_surface) {
    this.road_surface = road_surface;
    return this;
  }
  private Integer special_conds;
  public Integer get_special_conds() {
    return special_conds;
  }
  public void set_special_conds(Integer special_conds) {
    this.special_conds = special_conds;
  }
  public accident with_special_conds(Integer special_conds) {
    this.special_conds = special_conds;
    return this;
  }
  private Integer hazards;
  public Integer get_hazards() {
    return hazards;
  }
  public void set_hazards(Integer hazards) {
    this.hazards = hazards;
  }
  public accident with_hazards(Integer hazards) {
    this.hazards = hazards;
    return this;
  }
  private Integer area_type;
  public Integer get_area_type() {
    return area_type;
  }
  public void set_area_type(Integer area_type) {
    this.area_type = area_type;
  }
  public accident with_area_type(Integer area_type) {
    this.area_type = area_type;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof accident)) {
      return false;
    }
    accident that = (accident) o;
    boolean equal = true;
    equal = equal && (this.accident_index == null ? that.accident_index == null : this.accident_index.equals(that.accident_index));
    equal = equal && (this.lng == null ? that.lng == null : this.lng.equals(that.lng));
    equal = equal && (this.lat == null ? that.lat == null : this.lat.equals(that.lat));
    equal = equal && (this.date == null ? that.date == null : this.date.equals(that.date));
    equal = equal && (this.district == null ? that.district == null : this.district.equals(that.district));
    equal = equal && (this.road_c1 == null ? that.road_c1 == null : this.road_c1.equals(that.road_c1));
    equal = equal && (this.road_n1 == null ? that.road_n1 == null : this.road_n1.equals(that.road_n1));
    equal = equal && (this.road_type == null ? that.road_type == null : this.road_type.equals(that.road_type));
    equal = equal && (this.speed_limit == null ? that.speed_limit == null : this.speed_limit.equals(that.speed_limit));
    equal = equal && (this.junc_detail == null ? that.junc_detail == null : this.junc_detail.equals(that.junc_detail));
    equal = equal && (this.junc_control == null ? that.junc_control == null : this.junc_control.equals(that.junc_control));
    equal = equal && (this.road_c2 == null ? that.road_c2 == null : this.road_c2.equals(that.road_c2));
    equal = equal && (this.road_n2 == null ? that.road_n2 == null : this.road_n2.equals(that.road_n2));
    equal = equal && (this.cross_control == null ? that.cross_control == null : this.cross_control.equals(that.cross_control));
    equal = equal && (this.cross_facilities == null ? that.cross_facilities == null : this.cross_facilities.equals(that.cross_facilities));
    equal = equal && (this.light == null ? that.light == null : this.light.equals(that.light));
    equal = equal && (this.weather == null ? that.weather == null : this.weather.equals(that.weather));
    equal = equal && (this.road_surface == null ? that.road_surface == null : this.road_surface.equals(that.road_surface));
    equal = equal && (this.special_conds == null ? that.special_conds == null : this.special_conds.equals(that.special_conds));
    equal = equal && (this.hazards == null ? that.hazards == null : this.hazards.equals(that.hazards));
    equal = equal && (this.area_type == null ? that.area_type == null : this.area_type.equals(that.area_type));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof accident)) {
      return false;
    }
    accident that = (accident) o;
    boolean equal = true;
    equal = equal && (this.accident_index == null ? that.accident_index == null : this.accident_index.equals(that.accident_index));
    equal = equal && (this.lng == null ? that.lng == null : this.lng.equals(that.lng));
    equal = equal && (this.lat == null ? that.lat == null : this.lat.equals(that.lat));
    equal = equal && (this.date == null ? that.date == null : this.date.equals(that.date));
    equal = equal && (this.district == null ? that.district == null : this.district.equals(that.district));
    equal = equal && (this.road_c1 == null ? that.road_c1 == null : this.road_c1.equals(that.road_c1));
    equal = equal && (this.road_n1 == null ? that.road_n1 == null : this.road_n1.equals(that.road_n1));
    equal = equal && (this.road_type == null ? that.road_type == null : this.road_type.equals(that.road_type));
    equal = equal && (this.speed_limit == null ? that.speed_limit == null : this.speed_limit.equals(that.speed_limit));
    equal = equal && (this.junc_detail == null ? that.junc_detail == null : this.junc_detail.equals(that.junc_detail));
    equal = equal && (this.junc_control == null ? that.junc_control == null : this.junc_control.equals(that.junc_control));
    equal = equal && (this.road_c2 == null ? that.road_c2 == null : this.road_c2.equals(that.road_c2));
    equal = equal && (this.road_n2 == null ? that.road_n2 == null : this.road_n2.equals(that.road_n2));
    equal = equal && (this.cross_control == null ? that.cross_control == null : this.cross_control.equals(that.cross_control));
    equal = equal && (this.cross_facilities == null ? that.cross_facilities == null : this.cross_facilities.equals(that.cross_facilities));
    equal = equal && (this.light == null ? that.light == null : this.light.equals(that.light));
    equal = equal && (this.weather == null ? that.weather == null : this.weather.equals(that.weather));
    equal = equal && (this.road_surface == null ? that.road_surface == null : this.road_surface.equals(that.road_surface));
    equal = equal && (this.special_conds == null ? that.special_conds == null : this.special_conds.equals(that.special_conds));
    equal = equal && (this.hazards == null ? that.hazards == null : this.hazards.equals(that.hazards));
    equal = equal && (this.area_type == null ? that.area_type == null : this.area_type.equals(that.area_type));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.accident_index = JdbcWritableBridge.readInteger(1, __dbResults);
    this.lng = JdbcWritableBridge.readDouble(2, __dbResults);
    this.lat = JdbcWritableBridge.readDouble(3, __dbResults);
    this.date = JdbcWritableBridge.readDate(4, __dbResults);
    this.district = JdbcWritableBridge.readInteger(5, __dbResults);
    this.road_c1 = JdbcWritableBridge.readInteger(6, __dbResults);
    this.road_n1 = JdbcWritableBridge.readInteger(7, __dbResults);
    this.road_type = JdbcWritableBridge.readInteger(8, __dbResults);
    this.speed_limit = JdbcWritableBridge.readInteger(9, __dbResults);
    this.junc_detail = JdbcWritableBridge.readInteger(10, __dbResults);
    this.junc_control = JdbcWritableBridge.readInteger(11, __dbResults);
    this.road_c2 = JdbcWritableBridge.readInteger(12, __dbResults);
    this.road_n2 = JdbcWritableBridge.readInteger(13, __dbResults);
    this.cross_control = JdbcWritableBridge.readInteger(14, __dbResults);
    this.cross_facilities = JdbcWritableBridge.readInteger(15, __dbResults);
    this.light = JdbcWritableBridge.readInteger(16, __dbResults);
    this.weather = JdbcWritableBridge.readInteger(17, __dbResults);
    this.road_surface = JdbcWritableBridge.readInteger(18, __dbResults);
    this.special_conds = JdbcWritableBridge.readInteger(19, __dbResults);
    this.hazards = JdbcWritableBridge.readInteger(20, __dbResults);
    this.area_type = JdbcWritableBridge.readInteger(21, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.accident_index = JdbcWritableBridge.readInteger(1, __dbResults);
    this.lng = JdbcWritableBridge.readDouble(2, __dbResults);
    this.lat = JdbcWritableBridge.readDouble(3, __dbResults);
    this.date = JdbcWritableBridge.readDate(4, __dbResults);
    this.district = JdbcWritableBridge.readInteger(5, __dbResults);
    this.road_c1 = JdbcWritableBridge.readInteger(6, __dbResults);
    this.road_n1 = JdbcWritableBridge.readInteger(7, __dbResults);
    this.road_type = JdbcWritableBridge.readInteger(8, __dbResults);
    this.speed_limit = JdbcWritableBridge.readInteger(9, __dbResults);
    this.junc_detail = JdbcWritableBridge.readInteger(10, __dbResults);
    this.junc_control = JdbcWritableBridge.readInteger(11, __dbResults);
    this.road_c2 = JdbcWritableBridge.readInteger(12, __dbResults);
    this.road_n2 = JdbcWritableBridge.readInteger(13, __dbResults);
    this.cross_control = JdbcWritableBridge.readInteger(14, __dbResults);
    this.cross_facilities = JdbcWritableBridge.readInteger(15, __dbResults);
    this.light = JdbcWritableBridge.readInteger(16, __dbResults);
    this.weather = JdbcWritableBridge.readInteger(17, __dbResults);
    this.road_surface = JdbcWritableBridge.readInteger(18, __dbResults);
    this.special_conds = JdbcWritableBridge.readInteger(19, __dbResults);
    this.hazards = JdbcWritableBridge.readInteger(20, __dbResults);
    this.area_type = JdbcWritableBridge.readInteger(21, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(accident_index, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeDouble(lng, 2 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(lat, 3 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDate(date, 4 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeInteger(district, 5 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(road_c1, 6 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(road_n1, 7 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(road_type, 8 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(speed_limit, 9 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(junc_detail, 10 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(junc_control, 11 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(road_c2, 12 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(road_n2, 13 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(cross_control, 14 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(cross_facilities, 15 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(light, 16 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(weather, 17 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(road_surface, 18 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(special_conds, 19 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(hazards, 20 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(area_type, 21 + __off, 5, __dbStmt);
    return 21;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(accident_index, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeDouble(lng, 2 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(lat, 3 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDate(date, 4 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeInteger(district, 5 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(road_c1, 6 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(road_n1, 7 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(road_type, 8 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(speed_limit, 9 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(junc_detail, 10 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(junc_control, 11 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(road_c2, 12 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(road_n2, 13 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(cross_control, 14 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(cross_facilities, 15 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(light, 16 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(weather, 17 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(road_surface, 18 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(special_conds, 19 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(hazards, 20 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(area_type, 21 + __off, 5, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.accident_index = null;
    } else {
    this.accident_index = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.lng = null;
    } else {
    this.lng = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.lat = null;
    } else {
    this.lat = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.date = null;
    } else {
    this.date = new Date(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.district = null;
    } else {
    this.district = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.road_c1 = null;
    } else {
    this.road_c1 = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.road_n1 = null;
    } else {
    this.road_n1 = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.road_type = null;
    } else {
    this.road_type = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.speed_limit = null;
    } else {
    this.speed_limit = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.junc_detail = null;
    } else {
    this.junc_detail = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.junc_control = null;
    } else {
    this.junc_control = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.road_c2 = null;
    } else {
    this.road_c2 = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.road_n2 = null;
    } else {
    this.road_n2 = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.cross_control = null;
    } else {
    this.cross_control = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.cross_facilities = null;
    } else {
    this.cross_facilities = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.light = null;
    } else {
    this.light = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.weather = null;
    } else {
    this.weather = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.road_surface = null;
    } else {
    this.road_surface = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.special_conds = null;
    } else {
    this.special_conds = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.hazards = null;
    } else {
    this.hazards = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.area_type = null;
    } else {
    this.area_type = Integer.valueOf(__dataIn.readInt());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.accident_index) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.accident_index);
    }
    if (null == this.lng) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.lng);
    }
    if (null == this.lat) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.lat);
    }
    if (null == this.date) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.date.getTime());
    }
    if (null == this.district) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.district);
    }
    if (null == this.road_c1) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.road_c1);
    }
    if (null == this.road_n1) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.road_n1);
    }
    if (null == this.road_type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.road_type);
    }
    if (null == this.speed_limit) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.speed_limit);
    }
    if (null == this.junc_detail) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.junc_detail);
    }
    if (null == this.junc_control) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.junc_control);
    }
    if (null == this.road_c2) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.road_c2);
    }
    if (null == this.road_n2) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.road_n2);
    }
    if (null == this.cross_control) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.cross_control);
    }
    if (null == this.cross_facilities) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.cross_facilities);
    }
    if (null == this.light) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.light);
    }
    if (null == this.weather) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.weather);
    }
    if (null == this.road_surface) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.road_surface);
    }
    if (null == this.special_conds) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.special_conds);
    }
    if (null == this.hazards) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.hazards);
    }
    if (null == this.area_type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.area_type);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.accident_index) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.accident_index);
    }
    if (null == this.lng) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.lng);
    }
    if (null == this.lat) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.lat);
    }
    if (null == this.date) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.date.getTime());
    }
    if (null == this.district) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.district);
    }
    if (null == this.road_c1) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.road_c1);
    }
    if (null == this.road_n1) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.road_n1);
    }
    if (null == this.road_type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.road_type);
    }
    if (null == this.speed_limit) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.speed_limit);
    }
    if (null == this.junc_detail) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.junc_detail);
    }
    if (null == this.junc_control) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.junc_control);
    }
    if (null == this.road_c2) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.road_c2);
    }
    if (null == this.road_n2) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.road_n2);
    }
    if (null == this.cross_control) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.cross_control);
    }
    if (null == this.cross_facilities) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.cross_facilities);
    }
    if (null == this.light) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.light);
    }
    if (null == this.weather) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.weather);
    }
    if (null == this.road_surface) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.road_surface);
    }
    if (null == this.special_conds) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.special_conds);
    }
    if (null == this.hazards) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.hazards);
    }
    if (null == this.area_type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.area_type);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(accident_index==null?"null":"" + accident_index, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lng==null?"null":"" + lng, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lat==null?"null":"" + lat, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(date==null?"null":"" + date, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(district==null?"null":"" + district, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(road_c1==null?"null":"" + road_c1, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(road_n1==null?"null":"" + road_n1, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(road_type==null?"null":"" + road_type, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(speed_limit==null?"null":"" + speed_limit, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(junc_detail==null?"null":"" + junc_detail, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(junc_control==null?"null":"" + junc_control, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(road_c2==null?"null":"" + road_c2, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(road_n2==null?"null":"" + road_n2, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cross_control==null?"null":"" + cross_control, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cross_facilities==null?"null":"" + cross_facilities, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(light==null?"null":"" + light, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(weather==null?"null":"" + weather, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(road_surface==null?"null":"" + road_surface, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(special_conds==null?"null":"" + special_conds, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(hazards==null?"null":"" + hazards, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(area_type==null?"null":"" + area_type, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(accident_index==null?"null":"" + accident_index, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lng==null?"null":"" + lng, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lat==null?"null":"" + lat, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(date==null?"null":"" + date, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(district==null?"null":"" + district, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(road_c1==null?"null":"" + road_c1, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(road_n1==null?"null":"" + road_n1, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(road_type==null?"null":"" + road_type, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(speed_limit==null?"null":"" + speed_limit, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(junc_detail==null?"null":"" + junc_detail, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(junc_control==null?"null":"" + junc_control, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(road_c2==null?"null":"" + road_c2, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(road_n2==null?"null":"" + road_n2, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cross_control==null?"null":"" + cross_control, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cross_facilities==null?"null":"" + cross_facilities, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(light==null?"null":"" + light, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(weather==null?"null":"" + weather, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(road_surface==null?"null":"" + road_surface, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(special_conds==null?"null":"" + special_conds, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(hazards==null?"null":"" + hazards, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(area_type==null?"null":"" + area_type, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.accident_index = null; } else {
      this.accident_index = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lng = null; } else {
      this.lng = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lat = null; } else {
      this.lat = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.date = null; } else {
      this.date = java.sql.Date.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.district = null; } else {
      this.district = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.road_c1 = null; } else {
      this.road_c1 = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.road_n1 = null; } else {
      this.road_n1 = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.road_type = null; } else {
      this.road_type = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.speed_limit = null; } else {
      this.speed_limit = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.junc_detail = null; } else {
      this.junc_detail = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.junc_control = null; } else {
      this.junc_control = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.road_c2 = null; } else {
      this.road_c2 = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.road_n2 = null; } else {
      this.road_n2 = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.cross_control = null; } else {
      this.cross_control = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.cross_facilities = null; } else {
      this.cross_facilities = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.light = null; } else {
      this.light = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.weather = null; } else {
      this.weather = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.road_surface = null; } else {
      this.road_surface = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.special_conds = null; } else {
      this.special_conds = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.hazards = null; } else {
      this.hazards = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.area_type = null; } else {
      this.area_type = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.accident_index = null; } else {
      this.accident_index = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lng = null; } else {
      this.lng = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lat = null; } else {
      this.lat = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.date = null; } else {
      this.date = java.sql.Date.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.district = null; } else {
      this.district = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.road_c1 = null; } else {
      this.road_c1 = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.road_n1 = null; } else {
      this.road_n1 = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.road_type = null; } else {
      this.road_type = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.speed_limit = null; } else {
      this.speed_limit = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.junc_detail = null; } else {
      this.junc_detail = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.junc_control = null; } else {
      this.junc_control = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.road_c2 = null; } else {
      this.road_c2 = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.road_n2 = null; } else {
      this.road_n2 = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.cross_control = null; } else {
      this.cross_control = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.cross_facilities = null; } else {
      this.cross_facilities = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.light = null; } else {
      this.light = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.weather = null; } else {
      this.weather = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.road_surface = null; } else {
      this.road_surface = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.special_conds = null; } else {
      this.special_conds = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.hazards = null; } else {
      this.hazards = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.area_type = null; } else {
      this.area_type = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    accident o = (accident) super.clone();
    o.date = (o.date != null) ? (java.sql.Date) o.date.clone() : null;
    return o;
  }

  public void clone0(accident o) throws CloneNotSupportedException {
    o.date = (o.date != null) ? (java.sql.Date) o.date.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("accident_index", this.accident_index);
    __sqoop$field_map.put("lng", this.lng);
    __sqoop$field_map.put("lat", this.lat);
    __sqoop$field_map.put("date", this.date);
    __sqoop$field_map.put("district", this.district);
    __sqoop$field_map.put("road_c1", this.road_c1);
    __sqoop$field_map.put("road_n1", this.road_n1);
    __sqoop$field_map.put("road_type", this.road_type);
    __sqoop$field_map.put("speed_limit", this.speed_limit);
    __sqoop$field_map.put("junc_detail", this.junc_detail);
    __sqoop$field_map.put("junc_control", this.junc_control);
    __sqoop$field_map.put("road_c2", this.road_c2);
    __sqoop$field_map.put("road_n2", this.road_n2);
    __sqoop$field_map.put("cross_control", this.cross_control);
    __sqoop$field_map.put("cross_facilities", this.cross_facilities);
    __sqoop$field_map.put("light", this.light);
    __sqoop$field_map.put("weather", this.weather);
    __sqoop$field_map.put("road_surface", this.road_surface);
    __sqoop$field_map.put("special_conds", this.special_conds);
    __sqoop$field_map.put("hazards", this.hazards);
    __sqoop$field_map.put("area_type", this.area_type);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("accident_index", this.accident_index);
    __sqoop$field_map.put("lng", this.lng);
    __sqoop$field_map.put("lat", this.lat);
    __sqoop$field_map.put("date", this.date);
    __sqoop$field_map.put("district", this.district);
    __sqoop$field_map.put("road_c1", this.road_c1);
    __sqoop$field_map.put("road_n1", this.road_n1);
    __sqoop$field_map.put("road_type", this.road_type);
    __sqoop$field_map.put("speed_limit", this.speed_limit);
    __sqoop$field_map.put("junc_detail", this.junc_detail);
    __sqoop$field_map.put("junc_control", this.junc_control);
    __sqoop$field_map.put("road_c2", this.road_c2);
    __sqoop$field_map.put("road_n2", this.road_n2);
    __sqoop$field_map.put("cross_control", this.cross_control);
    __sqoop$field_map.put("cross_facilities", this.cross_facilities);
    __sqoop$field_map.put("light", this.light);
    __sqoop$field_map.put("weather", this.weather);
    __sqoop$field_map.put("road_surface", this.road_surface);
    __sqoop$field_map.put("special_conds", this.special_conds);
    __sqoop$field_map.put("hazards", this.hazards);
    __sqoop$field_map.put("area_type", this.area_type);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
