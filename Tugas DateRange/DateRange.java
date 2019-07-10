package com.gino;

import java.util.Iterator;
import java.time.LocalDate;

public class DateRange implements Iterable<LocalDate> {

    LocalDate pertama, terakhir, sekarang;

    public DateRange(LocalDate pertama, LocalDate terakhir) {
        this.pertama = pertama;
        this.terakhir = terakhir;
        this.sekarang = pertama;
    }

    public boolean overlap(DateRange daterange){
        return (contains(daterange.pertama) || contains(daterange.terakhir) || (daterange.pertama.isBefore(this.pertama) && daterange.terakhir.isAfter(this.terakhir)));
    }

    public boolean contains(LocalDate localdate){
        return ((localdate.isAfter(pertama) && localdate.isBefore(terakhir)) || localdate.equals(pertama) || localdate.equals(terakhir));
    }

    @Override
    public Iterator<LocalDate> iterator() {
        return new DateRangeIterator(this.pertama, this.terakhir);
    }
}

