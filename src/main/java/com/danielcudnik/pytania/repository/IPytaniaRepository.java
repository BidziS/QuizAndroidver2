package com.danielcudnik.pytania.repository;

import com.danielcudnik.pytania.ob.PytaniaOB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bidzis on 11/12/2016.
 */
@Repository
public interface IPytaniaRepository extends JpaRepository<PytaniaOB,Long> {
    @Query("SELECT u FROM PytaniaOB u WHERE u.kategorie.id=?1")
    List<PytaniaOB> znajdzPunktyPoKategorii(Long aIdKategoria);
    @Query("SELECT u FROM PytaniaOB u WHERE u.kategorie.nazwa =?1")
    List<PytaniaOB> znajdzPoNazwieKategorii(String aNick);
}
