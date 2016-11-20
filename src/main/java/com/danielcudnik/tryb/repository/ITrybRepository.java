package com.danielcudnik.tryb.repository;

import com.danielcudnik.tryb.ob.TrybOB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Bidzis on 11/9/2016.
 */
@Repository
public interface ITrybRepository extends JpaRepository<TrybOB,Long> {
    @Query("SELECT u FROM TrybOB u WHERE u.nazwa =?1")
    TrybOB znajdzPoNazwieTrybu(String aNazwaTrybu);

    @Query("SELECT u FROM TrybOB u WHERE u.nazwa =?1")
    TrybOB znajdzPoIdTrybu(Long aId);
}
