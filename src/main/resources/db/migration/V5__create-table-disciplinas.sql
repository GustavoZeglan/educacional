create table disciplinas (
    id integer primary key auto_increment,
    nome varchar(100),
    codigo varchar(20),
    curso_id integer,
    professor_id integer,
    foreign key (curso_id) references cursos(id),
    foreign key (professor_id) references professores(id)
);