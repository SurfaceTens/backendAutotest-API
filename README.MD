## Diagrama de la API
```
/api
├── /alumnos
│   ├── GET: all(void): List<AlumnoModel>
│   └── /{id}
│       ├── GET: one(void): AlumnoModel
│       ├── GET: preguntas(void): List<PreguntaModel>
│       ├── POST: add(AlumnoPostModel): AlumnoModel
│       ├── PUT: edit(AlumnoPostModel): AlumnoModel
│       └── DEL: delete/{id}(Long): HttpStatus
├── /preguntas
│   ├── GET: all(void): List<PreguntaModel>
│   └── /{id}
│       ├── GET: one(void): PreguntaModel
│       ├── GET: alumno(void): AlumnoModel
│       ├── POST: add(PreguntaPostModel): PreguntaModel
│       ├── PUT: edit(PreguntaPostModel): PreguntaModel
│       └── DEL: delete/{id}(Long): HttpStatus
├── /preguntaExamen
│   └── /{id}
│       ├── GET: one(void): PreguntaExamenModel
│       └── POST: add(PreguntaPostModel): PreguntaModel
└── /examenes
    └── /{id}
        └── GET: one(void): ExamenModel
```
