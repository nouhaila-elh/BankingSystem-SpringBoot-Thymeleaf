package org.lsi.metier;

import java.util.List;

import org.lsi.entities.Groupe;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

public interface GroupeMetier {
    public Groupe saveGroupe (Groupe e);
    public Page<Groupe> ListeGroupe(int page,int size);
}
