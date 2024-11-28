create table turmas (
    id integer primary key auto_increment,
    ano integer,
    semestre integer,
    curso_id integer
);

alter table turmas add constraint fk_curso_id foreign key (curso_id) references cursos(id);