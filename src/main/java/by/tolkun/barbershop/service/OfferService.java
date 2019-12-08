package by.tolkun.barbershop.service;

import by.tolkun.barbershop.entity.Offer;

import java.util.List;

public interface OfferService extends Service {
    List<Offer> findAll();

    Offer findByIdentity(int identity);

    void save(Offer user);

    void delete(int identity);
}
