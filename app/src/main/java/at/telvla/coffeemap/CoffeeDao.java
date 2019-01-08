package at.telvla.coffeemap;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CoffeeDao {
    @Query("SELECT * FROM coffee")
    List<Coffee> getAll();

    @Query("SELECT * FROM coffee WHERE id = :id")
    Coffee getById(long id);

    @Query("SELECT * FROM coffee WHERE id = :id")
    Coffee getByIdServer(Integer id);

    @Insert
    void insert(Coffee coffee);

    @Update
    void update(Coffee coffee);

    @Delete
    void delete(Coffee coffee);
}
