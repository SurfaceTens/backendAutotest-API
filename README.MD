## Diagrama de endpoints de la API
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
│
├── /preguntas
│   ├── GET: all(void): List<PreguntaModel>
│   └── /{id}
│       ├── GET: one(void): PreguntaModel
│       ├── GET: examenes(void): List<ExamenModel>
│       ├── POST: add(PreguntaPostModel): PreguntaModel
│       ├── PUT: edit(PreguntaPostModel): PreguntaModel
│       └── DEL: delete/{id}(Long): HttpStatus
│
├── /examenes
│   ├── GET: all(void): List<ExamenModel>
│   └── /{id}
│       ├── GET: one(void): ExamenModel
│       ├── GET: preguntas(void): List<PreguntaExamenModel>
│       ├── POST: add(ExamenPostModel): ExamenModel
│       ├── PUT: edit(ExamenModel): ExamenModel
│       └── DEL: delete/{id}(Long): HttpStatus
│
└── /preguntasExamen
    ├── GET: all(void): List<PreguntaExamenModel>
    ├── GET: generarExamen/{numPreguntas}/{idAlumno}/{dificultad}(int, Long, NivelDificultad): List<PreguntaExamenModel>
    ├── POST: add(PreguntaExamenPostModel): PreguntaExamenModel
    ├── PUT: edit(Long, PreguntaExamenModel): PreguntaExamenModel
    └── /{id}
        └── GET: one(void): PreguntaExamenModel

```
