package com.danielcudnik.uzytkownik.repository;


import com.danielcudnik.uzytkownik.ob.UzytkownikOB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bidzis on 11/3/2016.
 */
@Repository
public interface IUzytkownikRepository extends JpaRepository<UzytkownikOB,Long> {

    List<UzytkownikOB> findByNickStartsWith(String aNick);
    @Query("SELECT u FROM UzytkownikOB u WHERE u.nick =?1")
    UzytkownikOB znajdzPoNicku(String aNick);
}
