package com.danielcudnik.kategorie.repository;

import com.danielcudnik.kategorie.ob.KategorieOB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Bidzis on 11/12/2016.
 */
@Repository
public interface IKategorieRepository extends JpaRepository<KategorieOB,Long> {
    @Query("SELECT u FROM KategorieOB u WHERE u.nazwa =?1")
    KategorieOB znajdzPoNazwieKategorii(String aNazwaKategorii);
}
